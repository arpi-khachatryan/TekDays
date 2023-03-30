package com.tekdays

import grails.transaction.Transactional

@Transactional
class TestSchedulerService {

   def print(){
       print "ok"
   }
}
