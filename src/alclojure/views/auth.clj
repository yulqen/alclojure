(ns alclojure.views.auth
  (:require [alclojure.views.layout :as layout]))

(defn signup-page [& [params]]
  (layout/application "Alphabet Learning - Sign Up"
                     [:div.max-w-md.mx-auto.bg-white.p-8.rounded-lg.shadow-md
                      [:h2.text-2xl.font-bold.mb-6.text-center "Create an Account"]
                      
                      (when (:error params)
                        [:div.bg-red-100.text-red-700.p-3.rounded-lg.mb-4
                         (:error params)])
                      
                      [:form {:method "POST" :action "/signup"}
                       [:div.mb-4
                        [:label.block.text-gray-700.mb-2 {:for "email"} "Email"]
                        [:input#email.w-full.px-4.py-2.border.rounded-lg.focus:outline-none.focus:ring-2.focus:ring-blue-500
                         {:type "email" :name "email" :required true}]]
                       
                       [:div.mb-6
                        [:label.block.text-gray-700.mb-2 {:for "password"} "Password"]
                        [:input#password.w-full.px-4.py-2.border.rounded-lg.focus:outline-none.focus:ring-2.focus:ring-blue-500
                         {:type "password" :name "password" :required true}]]
                       
                       [:div.mb-6
                        [:button.w-full.bg-blue-600.text-white.py-2.px-4.rounded-lg.font-medium.hover:bg-blue-700.transition
                         {:type "submit"}
                         "Sign Up"]]
                       
                       [:div.text-center.text-gray-600
                        "Already have an account? "
                        [:a.text-blue-600.hover:underline {:href "/login"} "Log In"]]]]))

(defn login-page [& [params]]
  (layout/application "Alphabet Learning - Log In"
                     [:div.max-w-md.mx-auto.bg-white.p-8.rounded-lg.shadow-md
                      [:h2.text-2xl.font-bold.mb-6.text-center "Log In to Your Account"]
                      
                      (when (:error params)
                        [:div.bg-red-100.text-red-700.p-3.rounded-lg.mb-4
                         (:error params)])
                      
                      [:form {:method "POST" :action "/login"}
                       [:div.mb-4
                        [:label.block.text-gray-700.mb-2 {:for "email"} "Email"]
                        [:input#email.w-full.px-4.py-2.border.rounded-lg.focus:outline-none.focus:ring-2.focus:ring-blue-500
                         {:type "email" :name "email" :required true}]]
                       
                       [:div.mb-6
                        [:label.block.text-gray-700.mb-2 {:for "password"} "Password"]
                        [:input#password.w-full.px-4.py-2.border.rounded-lg.focus:outline-none.focus:ring-2.focus:ring-blue-500
                         {:type "password" :name "password" :required true}]]
                       
                       [:div.mb-6
                        [:button.w-full.bg-blue-600.text-white.py-2.px-4.rounded-lg.font-medium.hover:bg-blue-700.transition
                         {:type "submit"}
                         "Log In"]]
                       
                       [:div.text-center.text-gray-600
                        "Don't have an account? "
                        [:a.text-blue-600.hover:underline {:href "/signup"} "Sign Up"]]]]))