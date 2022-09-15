
#include <iostream>
#include <grpcpp/grpcpp.h>

#include "protos/hello.grpc.pb.h"
#include "config/config.h"

class Service : public hello::Greeter::Service {
public:
  grpc::Status SayHello(grpc::ServerContext *context,
                        const hello::HelloRequest *request,
                        hello::HelloReply *response) override {
    response->set_message("Hello there, " + request->name() + "!");
    return grpc::Status::OK;
  }
};

int main() {
  Service service;
  auto address = "0.0.0.0:" + std::to_string(hello::PORT);
  auto server =
      grpc::ServerBuilder()
          .AddListeningPort(address, grpc::InsecureServerCredentials())
          .RegisterService(&service)
          .BuildAndStart();
  std::cerr << "Listening on " << address << "\n";
  server->Wait();
  return 0;
}
