(ns alclojure.views.resources
  (:require [alclojure.views.layout :as layout]))

(defn resource-card [resource]
  [:div.bg-white.rounded-lg.shadow-md.overflow-hidden
   [:img.w-full.h-48.object-cover {:src (str "/img/thumbnails/" (first (:thumbnail_filenames resource)))
                                   :alt (:name resource)}]
   [:div.p-4
    [:div.flex.items-center.mb-2
     [:span.text-xs.px-2.py-1.rounded.bg-blue-100.text-blue-800 
      (get-in resource [:main_resource_category :name])]]
    [:h3.text-lg.font-semibold.mb-2 (:name resource)]
    [:p.text-sm.text-gray-600.mb-4 (:card_description resource)]
    [:div.flex.justify-between.items-center
     [:span.font-bold "£1.50"]
     [:a.bg-blue-600.text-white.px-4.py-2.rounded.text-sm.hover:bg-blue-700 
      {:href (str "/resources/" (:id resource))} "View Details"]]]]) 

(defn index-page [resources]
  (layout/application "Alphabet Learning - Resources"
                     [:div.mb-8
                      [:h1.text-3xl.font-bold.mb-4 "Educational Resources"]
                      [:p "Browse our collection of high-quality educational worksheets."]]
                     
                     [:div.grid.grid-cols-1.md:grid-cols-2.lg:grid-cols-3.gap-6
                      (map resource-card resources)]))

(defn show-page [resource]
  (layout/application (str "Alphabet Learning - " (:name resource))
                     [:div.bg-white.rounded-lg.shadow-md.p-6
                      [:div.flex.justify-between.items-start.mb-6
                       [:h1.text-3xl.font-bold (:name resource)]
                       [:span.bg-blue-100.text-blue-800.px-3.py-1.rounded-full.text-sm
                        (get-in resource [:main_resource_category :name])]]
                      
                      [:div.grid.grid-cols-1.md:grid-cols-2.gap-8
                       [:div
                        [:div.mb-6
                         [:img.w-full.rounded-lg.shadow-md
                          {:src (str "/img/thumbnails/" (first (:thumbnail_filenames resource)))
                           :alt (:name resource)}]]
                        
                        [:div.mb-6
                         [:h3.text-lg.font-semibold.mb-2 "Details"]
                         [:ul.text-gray-700.space-y-2
                          [:li [:span.font-medium "Age Range: "] (:age_range resource)]
                          [:li [:span.font-medium "Curriculum: "] (:curriculum resource)]]]]
                       
                       [:div
                        [:div.mb-6
                         [:h2.text-2xl.font-bold.mb-4 "About this resource"]
                         [:p.text-gray-700 (:description resource)]]
                        
                        [:div.bg-gray-50.p-4.rounded-lg.mb-6
                         [:h3.text-lg.font-semibold.mb-2 "Price"]
                         [:p.text-3xl.font-bold.text-blue-600 "£1.50"]]
                        
                        [:button.w-full.bg-blue-600.text-white.py-3.px-4.rounded-lg.font-medium.hover:bg-blue-700.transition
                         {:type "submit"}
                         "Add to Cart"]]]]))
