(ns tripmap.core
  (:gen-class)
  (:require [clojure.data.json :as json])
  (:require [clj-http.client :as http])
  (:require [ring.util.codec :as codec]))

(def directions-endpoint "https://maps.googleapis.com/maps/api/directions/json?")
(def map-endpoint "https://maps.googleapis.com/maps/api/staticmap?scale=2&")

(defn get-json
  [url]
  (json/read-str (:body (http/get url)) :key-fn keyword))

(defn get-map
  [url]
  (let [{status :status body :body headers :headers} (http/get url {:as :stream})]
    (if (= status 200)
      body)))

(defn load-trip
  "Loads a trip JSON into a record from a file"
  [path]
  (let [contents (slurp path)]
    (json/read-str contents :key-fn keyword)))

(defn get-direction-query 
  [{start :from end :to waypoints :waypoints}]
  (str directions-endpoint 
       "origin=" start 
       "&destination=" end 
       "&waypoints=" (clojure.string/join "|" waypoints)))

(defn get-map-query
  [width height points]
  (str map-endpoint "size=" width "x" height "&path=enc:" (codec/url-encode points)))


(defn get-overview-polyline
  [url]
  (-> url
      get-json
      :routes
      first
      :overview_polyline
      :points))

(defn json->map
  [width height]
  (comp (partial get-map-query width height)
                       get-overview-polyline 
                       get-direction-query))

(defn save-img
  [img filename]
  (clojure.java.io/copy img (java.io.File. filename)))

(defn -main
  [& args]
  (let [{
         {width :width height :height output :output} :options
         trip :trip} (load-trip "/home/thomas/Documenti/trips/trip1.json")
         map-url ((json->map width height) trip)
         img (get-map map-url)
        ]
    ;;(println ((json->map width height) trip))
    (save-img img output)
    ))



















