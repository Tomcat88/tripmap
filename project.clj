(defproject tripmap "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-http "2.0.0"]
                 [ring/ring-codec "1.0.0"]]
  :main ^:skip-aot tripmap.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
