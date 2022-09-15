/*
 * Copyright 2015 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// this file was modified from the original at
// https://github.com/grpc/grpc-java/blob/341fea8996fbd48f40140636e72e766e31e32852/examples/src/main/java/io/grpc/examples/helloworld/HelloWorldClient.java

package hello;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloClient {
  private static final Logger logger = Logger.getLogger(HelloClient.class.getName());

  private final GreeterGrpc.GreeterBlockingStub stub;

  public HelloClient(Channel channel) {
    blockingStub = GreeterGrpc.newBlockingStub(channel);
  }

  public void greet(String name, String host) {
    HelloRequest request = HelloRequest.newBuilder().setName(name).build();
    HelloReply response;
    try {
      response = stub.sayHello(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      System.exit(1);
    }
    logger.info(host + " replied with \"" + response.getMessage() + "\"");
  }

  public static void main(String[] args) throws Exception {
    String user = "world";
    String target = "localhost:" + String.valueOf(Config.PORT);
    if (args.length > 0) {
      user = args[0];
    }

    ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
        .usePlaintext()
        .build();
    try {
      HelloClient client = new HelloClient(channel);
      client.greet(user, target);
    } finally {
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }
}
