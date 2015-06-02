(ns tailrecursion.boot-proxy
  {:boot/export-tasks true}
  (:refer-clojure :exclude [proxy])
  (:require
    [boot.core :refer :all]
    [boot.pod  :as    pod]
    [boot.util :refer [info]] ))

(def ^:private deps
  '[[tailrecursion/ring-proxy "3.0.0-SNAPSHOT"] ])

(defn make-pod []
  (-> (get-env) (update-in [:dependencies] into deps) pod/make-pod future) )

(deftask serve-proxy
  "Create a proxy server."
  [t listen-path PATH    str      "The local path the server will listen on (/)"
   p listen-port PORT    int      "The local port the server will bind to (80)."
   u remote-uri URI      str      "The base uri of the remote server."
   o opts KEY=VAL        {kw str} "The clj-http request options map."]
  (assert remote-uri "Missing remote URI.")
  (let [pod  (make-pod)
        path (or listen-path "/")
        port (or listen-port 80) ]
    (info "Proxying path %s on port %s to %s...\n" path port remote-uri)
    (with-pre-wrap fileset
      (pod/with-call-in @pod
        (tailrecursion.ring-proxy/run-proxy ~path ~port ~remote-uri ~opts) )
      fileset )))