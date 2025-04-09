(ns alclojure.views.layout
  (:require [hiccup.page :as page]))

(defn application
  [title & content]
  (page/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1, shrink-to-fit=no"}]
    [:title title]
    (page/include-css "/css/output.css")]
   [:body
    [:header.bg-blue-600.text-white.p-4
     [:div.container.mx-auto.flex.justify-between.items-center
      [:a.text-xl.font-bold {:href "/"} "Alphabet Learning"]
      [:nav
       [:ul.flex.space-x-4
        [:li [:a.hover:underline {:href "/"} "Home"]]
        [:li [:a.hover:underline {:href "/resources"} "Resources"]]
        [:li [:a.hover:underline {:href "/login"} "Login"]]
        [:li [:a.hover:underline {:href "/signup"} "Sign Up"]]]]]
     ]
    [:main.container.mx-auto.py-8.px-4
     content]
    [:footer.bg-gray-100.py-6.mt-12
     [:div.container.mx-auto.text-center
      [:p "© 2023 Alphabet Learning. All rights reserved."]]]
    
    ;; JavaScript
    (page/include-js "/js/main.js")]))