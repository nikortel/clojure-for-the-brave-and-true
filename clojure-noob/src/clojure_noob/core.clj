(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I am a little teapot!"))

(defn train
  []
  (println "Choo Choo!"))

(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OH. MY. GOG! " name " YOU ARE MOST DEFINITELY LIKE THE BEST " "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))

(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(defn inc-maker
  "Create custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          [] ;default value for the reduce
          asym-body-parts))

(defn create-full-hobbit
  []
  (def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])
  (println "Creating a hobbit")
  (symmetrize-body-parts asym-hobbit-body-parts))

(defn hit
  [body-parts]
  (let [body-part-size-sum (reduce + (map :size body-parts)) target (rand body-part-size-sum)]
    (loop [[part & remaining] body-parts accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))
