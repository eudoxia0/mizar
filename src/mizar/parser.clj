(ns mizar.parser
  "Mizar's parser."
  (:require [instaparse.core :as insta]))

(def grammar
  (insta/parser
    "program = defn

     (* Toplevel Expressions *)
     defn = type <space>+ ident arglist <space>+ block
     arglist = '(' argument* ')'
     argument = <space>* ident ':' type <space>*

     (* Types *)
     type = 'bool' | 'int' | 'float' | ident

     (* Statement *)
     statement = <space>* (if | block | return) <space>*
     if = 'if' expression 'then' statement 'else' statement
     block = 'begin' statement+ 'end'
     return = 'return' expression

     (* Expressions *)
     expression = <space>* (binop | constant | ident) <space>*
     binop = expression ('+'|'-'|'*'|'/'|'=') expression

     (* Constants *)
     constant = bool | integer | float
     bool = 'true' | 'false'
     integer = <sign><intseq>
     float = <sign><intseq>'.'<intseq>

     (* Identifiers *)
     ident = #'[a-zA-Z_]+'

     (* Auxiliary *)
     <space> = #'\\s'
     <sign> = '-'*
     <intseq> = #'[0-9]+'"
    :output-format :hiccup))
