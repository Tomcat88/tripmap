(ns tripmap.core
  (:gen-class)
  (:require [clojure.data.json :as json])
  (:require [clj-http.client :as http]))

(def directions-endpoint "https://maps.googleapis.com/maps/api/directions/json?")

(defn load-trip
  "Loads a trip JSON into a record from a file"
  [path]
  (let [contents (slurp path)]
    (json/read-str contents :key-fn keyword)))

(defn get-direction-query 
  [{start :from end :to waypoints :waypoints}]
  (str directions-endpoint "origin=" start "&destination=" end "&waypoints=" (clojure.string/join "|" waypoints)))

(defn get-json
  [url]
  (json/read-str (:body (http/get url)) :key-fn keyword))

(defn get-overview-polyline
  [response-map]
  ((comp :points :overview_polyline first :routes) response-map))

(defn -main
  [& args]
  (println "Hello, World!")
  (let [trip (load-trip "/home/thomas/Documenti/trips/trip1.json")]
    (println trip)
    trip))













