(ns clj4tw.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [clj4tw.events :as events]
   [clj4tw.routes :as routes]
   [clj4tw.views :as views]
   [clj4tw.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
