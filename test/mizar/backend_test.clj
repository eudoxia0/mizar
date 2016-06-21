(ns mizar.ast-test
  (:require [clojure.test :refer :all]
            [mizar.parser :refer [grammar]]
            [mizar.ast :refer [transform]]
            [mizar.backend :refer :all]))
