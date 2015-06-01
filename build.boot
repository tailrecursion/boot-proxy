(set-env!
  :resource-paths #{"src"}
  :dependencies   '[[org.clojure/clojure "1.6.0"  :scope "provided"]
                    [adzerk/bootlaces    "0.1.10" :scope "test"] ])

(refer-clojure :exclude '[proxy])
(require
  '[adzerk.bootlaces         :refer :all]
  '[tailrecursion.boot-proxy :refer [proxy]] )

(def +version+ "1.0.0-SNAPSHOT")

(task-options!
 pom  {:project     'tailrecursion/boot-proxy
       :version     +version+
       :description "Boot proxy server."
       :scm         {:url "https://github.com/tailrecursion/boot-proxy"}
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"} })