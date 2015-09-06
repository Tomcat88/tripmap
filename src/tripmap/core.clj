(ns tripmap.core
  (:gen-class)
  (:require [clojure.data.json :as json]))

(def directions-endpoint "https://maps.googleapis.com/maps/api/directions/json?")

(defn load-trip
  "Loads a trip JSON into a record from a file"
  [path]
  (let [contents (slurp path)]
    (json/read-str contents :key-fn keyword)))

(defn get-direction-query 
  [{start :start end :end waypoints :waypoints}]
  (str directions-endpoint "origin=" start "&destination=" end "&waypoints=" (clojure.string/join "|" waypoints)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (println (load-trip "/home/thomas/Documenti/trips/trip1.json")))













