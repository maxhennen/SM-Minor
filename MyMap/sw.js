const staticCacheName = 'site-static-v3';
const dynamicCacheName = 'site-dynamic-v2';

const assets = [
    '/',
    '/index.html',
    '/js/add-to-bucketlist.js',
    '/js/worldmap.js',
    '/css/style.css',
    '/pages/bucket-list.html',
    '/manifest.json',
    '/pages/bucket-list-item.html',
    '/js/bucketlistitem.js',
    '/img/mapNA.png',
];


// install event
self.addEventListener('install', evt => {
    //console.log('service worker installed');
    evt.waitUntil(
        caches.open(staticCacheName).then((cache) => {
            console.log('caching shell assets');
            return cache.addAll(assets);
        })
    );
});

// activate event
self.addEventListener('activate', evt => {
    //console.log('service worker activated');
    evt.waitUntil(
        caches.keys().then(keys => {
            //console.log(keys);
            return Promise.all(keys
                .filter(key => key !== staticCacheName && key !== dynamicCacheName)
                .map(key => caches.delete(key))
            );
        })
    );
});

// fetch event
self.addEventListener('fetch', evt => {
    //console.log('fetch event', evt);
    evt.respondWith(
        caches.match(evt.request).then(cacheRes => {
            return cacheRes || fetch(evt.request).then(fetchRes => {
                return caches.open(dynamicCacheName).then(cache => {
                    cache.put(evt.request.url, fetchRes.clone());
                    return fetchRes;
                })
            });
        }).catch(() => caches.match('/pages/fallback.html'))
    );
});