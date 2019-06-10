(ns conversor.cambista
  (:require [clj-http.client :as http-client]
            [cheshire.core :refer [parse-string]]))

(def chave "f9e050ca3ca2cc3216d0")
(def api-url "https://free.currencyconverterapi.com/api/v6/convert")

(defn- parametrizar-moedas [de para]
  (str de "_" para))

(defn obter-cotacao [de para]
  (let [moedas (parametrizar-moedas de para)]
    (-> (:body (http-client/get api-url
                                {:query-params { "q" moedas
                                                "apiKey" chave}}))
        (get-in ["results" moedas "val"]))))