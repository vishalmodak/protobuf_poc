# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: prospect.proto

require 'google/protobuf'

require 'google/protobuf/any_pb'
Google::Protobuf::DescriptorPool.generated_pool.build do
  add_message "com.lending.proto.Prospect" do
    optional :prospectId, :int64, 1
    optional :anyProspect, :message, 4, "google.protobuf.Any"
    oneof :prospectType do
      optional :business, :message, 2, "com.lending.proto.Business"
      optional :person, :message, 3, "com.lending.proto.Person"
    end
  end
  add_message "com.lending.proto.Business" do
    optional :businessId, :int64, 1
    optional :name, :string, 2
    optional :naics, :string, 3
    optional :fein, :string, 4
  end
  add_message "com.lending.proto.Person" do
    optional :personId, :int64, 1
    optional :firstName, :string, 2
    optional :lastName, :string, 3
    optional :governmentId, :string, 4
  end
end

module Com
  module Lending
    module Proto
      Prospect = Google::Protobuf::DescriptorPool.generated_pool.lookup("com.lending.proto.Prospect").msgclass
      Business = Google::Protobuf::DescriptorPool.generated_pool.lookup("com.lending.proto.Business").msgclass
      Person = Google::Protobuf::DescriptorPool.generated_pool.lookup("com.lending.proto.Person").msgclass
    end
  end
end
