// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: domain-protos/prospect.proto

package com.lending.proto;

public final class ProspectOuterClass {
  private ProspectOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_lending_proto_Prospect_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_lending_proto_Prospect_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_lending_proto_Business_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_lending_proto_Business_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_lending_proto_Person_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_lending_proto_Person_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\034domain-protos/prospect.proto\022\021com.lend" +
      "ing.proto\032\031google/protobuf/any.proto\"\267\001\n" +
      "\010Prospect\022\022\n\nprospectId\030\001 \001(\003\022/\n\010busines" +
      "s\030\002 \001(\0132\033.com.lending.proto.BusinessH\000\022+" +
      "\n\006person\030\003 \001(\0132\031.com.lending.proto.Perso" +
      "nH\000\022)\n\013anyProspect\030\004 \001(\0132\024.google.protob" +
      "uf.AnyB\016\n\014prospectType\"I\n\010Business\022\022\n\nbu" +
      "sinessId\030\001 \001(\003\022\014\n\004name\030\002 \001(\t\022\r\n\005naics\030\003 " +
      "\001(\t\022\014\n\004fein\030\004 \001(\t\"U\n\006Person\022\020\n\010personId\030" +
      "\001 \001(\003\022\021\n\tfirstName\030\002 \001(\t\022\020\n\010lastName\030\003 \001",
      "(\t\022\024\n\014governmentId\030\004 \001(\tB\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.AnyProto.getDescriptor(),
        }, assigner);
    internal_static_com_lending_proto_Prospect_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_lending_proto_Prospect_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_lending_proto_Prospect_descriptor,
        new java.lang.String[] { "ProspectId", "Business", "Person", "AnyProspect", "ProspectType", });
    internal_static_com_lending_proto_Business_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_lending_proto_Business_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_lending_proto_Business_descriptor,
        new java.lang.String[] { "BusinessId", "Name", "Naics", "Fein", });
    internal_static_com_lending_proto_Person_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_lending_proto_Person_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_lending_proto_Person_descriptor,
        new java.lang.String[] { "PersonId", "FirstName", "LastName", "GovernmentId", });
    com.google.protobuf.AnyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
