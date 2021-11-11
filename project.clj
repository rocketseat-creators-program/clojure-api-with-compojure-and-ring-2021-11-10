 (defproject awesome "0.1.0-SNAPSHOT"
   :description "FIXME: write description"
   :dependencies [[org.clojure/clojure "1.10.0"]
                  [metosin/compojure-api "2.0.0-alpha30"]
                  [prismatic/schema-generators "0.1.3"]]
   :ring {:handler awesome.system/app}
   :uberjar-name "server.jar"
   :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]
                                   [ring/ring-jetty-adapter "1.9.4"]]
                    :plugins [[lein-ring "0.12.5"]]}})
