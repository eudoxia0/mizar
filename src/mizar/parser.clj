(ns mizar.parser
  "Mizar's parser."
  (:require [instaparse.core :as insta]))

(def grammar
  (insta/parser
    "program = <space>* defn <space>*

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
     expression = <space>* (binop | constant | call | ident) <space>*
     binop = expression ('+'|'-'|'*'|'/'|'=') expression
     call = ident '(' expression* ')'

     (* Constants *)
     constant = bool | float | integer | string
     bool = 'true' | 'false'
     integer = #'[-]?[0-9]+'
     float = #'[+-]?[0-9]*\\.?[0-9]+([eE][+-]?[0-9]+)?'
     string = #'(\\\"[^\"]*\\\")'

     (* Identifiers *)
     ident = #'[a-zA-Z_]+'

     (* Auxiliary *)
     <space> = #'\\s'"
    :output-format :hiccup))
