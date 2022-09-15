To build the server and client
```bash
bazel build //client //server
```
Client executable will be available as
``` 
bazel-bin/client/client
```
Server executable will be available as
```
bazel-bin/server/server
```
You can build and run with a one-liner
```bash
bazel run //server
```
or
```bash
bazel run //client
```

To generate a graph of dependencies:

```bash
bazel cquery "deps(//server + //client) intersect //..." --output=graph | dot -Tjpeg > graph.jpeg
```
