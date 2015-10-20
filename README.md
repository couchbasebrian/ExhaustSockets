# ExhaustSockets
Test program used to deliberately exhaust all the sockets 

You can monitor this program from the client side with a loop like this

```
$ while [ 1 ]; do netstat -an | grep 11210 | wc -l; sleep 1; done
       0
       0
       0
       0
    1000
    2000
    2000
    2000
    2000
    2000
    2000
    2000
    4000
    4000
```

Reference:

* http://docs.couchbase.com/developer/java-2.1/env-config.html
* http://docs.couchbase.com/sdk-api/couchbase-java-client-2.1.3/com/couchbase/client/java/env/DefaultCouchbaseEnvironment.Builder.html#kvEndpoints-int-
