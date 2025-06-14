(ns alclojure.views.layout
  (:require [hiccup.page :as page]))

(defn application
  [user title & content]
  (page/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1, shrink-to-fit=no"}]
    [:title title]
    (page/include-css "/css/output.css")]
   [:body
    [:header.bg-red-400.text-white.p-4
     [:div.container.mx-auto.flex.justify-between.items-center
      [:a {:href "/"}
       [:img {:src "/img/thumbnails/AL_long_logo_black_grey.png"
              :width 220
              :height 220}]]
      [:nav
       [:ul.flex.space-x-4
        [:li [:a.hover:underline {:href "/"} "Home"]]
        [:li [:a.hover:underline {:href "/resources"} "Resources"]]
        (if user
          [:li [:a.hover:underline {:href "/logout"} (format "Signed in as %s" user)]]
          [:li [:a.hover:underline {:href "/login"} "Login"]])
        (when-not user
          [:li [:a.hover:underline {:href "/signup"} "Sign Up"]])]]]
    ]
    [:main.container.mx-auto.py-8.px-4.bg-red-100
     content]
    [:footer.bg-gray-100.py-6.mt-12
     [:div.container.mx-auto.text-center
      [:p "© 2025 Alphabet Learning. All rights reserved."]]]
    (page/include-js "/js/main.js")]))
