
[![Build Status](https://travis-ci.org/genericmethod/num-to-text.svg?branch=develop)](https://travis-ci.org/genericmethod/num-to-text)
[![codecov.io](http://codecov.io/github/genericmethod/num-to-text/coverage.svg?branch=develop)](http://codecov.io/github/genericmethod/num-to-text?branch=develop)
[![Codeship Status for genericmethod/num-to-text](https://codeship.com/projects/58bbbb40-3c3c-0133-e335-3ebbb4d77cd4/status?branch=develop)](https://codeship.com/projects/102232)

**Number to Text Converter**

Couple of classes to convert a number into words.
Used this project as an excuse to try out Travis CI, Codeship and Heroku
Exposed the conversion as a REST call and deployed to a free dyno.

**How it works**

Call the following url and replace the number to be converted as a path variable:

    http://shielded-dawn-4230.herokuapp.com/num2text/{number}

ex: 
http://shielded-dawn-4230.herokuapp.com/num2text/123 returns ***"one hundred twenty three"***
http://shielded-dawn-4230.herokuapp.com/num2text/929399654423132376 returns ***"nine hundred twenty nine quadrillion three hundred ninety nine trillion six hundred fifty four billion four hundred twenty three million one hundred thirty two thousand three hundred seventy six"***
