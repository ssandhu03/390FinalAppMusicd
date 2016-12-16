(ns inclass-oct-31.core
  (:gen-class))


(defn arithOp
  [src level]
  (if (or
        (= src '+)
        (= src '-)
        )
    (do
      (println level "arithOp: " src)
      true
      )
    (do
      (println level "arithOp error: " src)
      false
      )
    )
  )
(defn findId
  [src level]
  (if (or
        (= src 'a)
        (= src 'b)
        (= src 'c)
        )
    (do
      (println level "id: " src)
      true
      )
    (do
      (println level "id error: " src)
      false
      )
    )
  )
(defn expr
  ([src]
   (println "Expression:")
   (expr src "\t"))
  ([src level]
   (cond
     (empty? (rest src))  ;; found a single symbol
     (findId (first src) level)
     (and (findId (first src) level)
          (arithOp (first (rest src)) level))
     (expr (rest (rest src)) level)
     :else (println "expr error: " src)
     )
    )
  )
(defn assign
  ([src]
   (println "Assign: ")
   (cond
     (and (findId (first src) "")
          (= (first (rest src)) '=))
     (do
       (println '= " ")
       (expr (first (rest (rest src))))
       )
     :else (println "assign error: " src)
     )
    )
  )



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
