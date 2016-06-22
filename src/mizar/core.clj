(ns mizar.core
  (:gen-class)
  (:require [mizar.parser :refer [grammar]]
            [mizar.ast :refer [transform]]
            [mizar.backend :refer [emit]]))

(defn -main
  "Entry point."
  [& args]
  (let [filename (first args)
        filepath (-> (java.io.File. filename) .getAbsolutePath)
        content (slurp filepath)]
    (println (-> content
                 grammar
                 transform
                 emit))))
