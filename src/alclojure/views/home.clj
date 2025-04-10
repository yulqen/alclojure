(ns alclojure.views.home
  (:require [alclojure.views.layout :as layout]))

(defn index-page []
  (layout/application "Alphabet Learning - Home"
                     [:div.text-center
                      [:h1.text-4xl.font-bold.mb-4 "Welcome to Alphabet Learning!"]
                      [:p.text-xl.mb-8 "High quality educational resources focused on simple concepts and the first steps in learning."]
                      [:div.flex.justify-center
                       [:a.bg-red-600.text-white.px-6.py-3.rounded-lg.hover:bg-red-700.mr-4 
                        {:href "/resources"} "Browse Resources"]
                       [:a.bg-red-200.px-6.py-3.rounded-lg.hover:bg-red-300 
                        {:href "/signup"} "Sign Up"]]]                     
                     [:div.grid.grid-cols-1.md:grid-cols-3.gap-8.mt-16
                      [:div.bg-white.p-6.rounded-lg.shadow-md
                       [:h2.text-xl.font-bold.mb-2 "For Teachers"]
                       [:p "High-quality worksheet resources aligned with curriculum standards."]]
                      [:div.bg-white.p-6.rounded-lg.shadow-md
                       [:h2.text-xl.font-bold.mb-2 "For Parents"]
                       [:p "Support your child's learning journey with fun and engaging materials."]]
                      [:div.bg-white.p-6.rounded-lg.shadow-md
                       [:h2.text-xl.font-bold.mb-2 "For Tutors"]
                       [:p "Supplement your teaching with professionally designed worksheets."]]]))
