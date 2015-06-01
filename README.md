# boot-proxy

HTTP proxy [ring middleware](https://github.com/ring-clojure/ring/blob/a02518275a06835e4fdd1a3af59d7c4c0408d25b/SPEC#L12)
for Clojure web applications.

### Dependency

```clojure
[tailrecursion/boot-proxy "1.0.0-SNAPSHOT"]
```

### Command Line

Suppose you need a local proxy server for some reason (I needed one to be able
to make SOAP requests using Apache Axis2 to a test server whose SSL certificate
was invalid --- there was no easy way to tell Axis2 to pass those arguments to
the HTTP client it uses to make the requests).

```
$ boot proxy -t /forward -p 7000 -u https://example.com -o insecure=true
```

You can now make requests to http://localhost:7000/foo/bar, for instance, and
the proxy will relay the request over SSL using the HTTP client options you
provide on the command line.

## License

Copyright © 2013 Alan Dipert and Micha Niskin

Distributed under the Eclipse Public License, the same as Clojure.
