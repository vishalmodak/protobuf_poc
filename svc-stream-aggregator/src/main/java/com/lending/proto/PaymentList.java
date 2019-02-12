// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: domain-protos/payment.proto

package com.lending.proto;

/**
 * Protobuf type {@code com.lending.proto.PaymentList}
 */
public  final class PaymentList extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.lending.proto.PaymentList)
    PaymentListOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PaymentList.newBuilder() to construct.
  private PaymentList(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PaymentList() {
    payments_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PaymentList(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              payments_ = new java.util.ArrayList<com.lending.proto.Payment>();
              mutable_bitField0_ |= 0x00000001;
            }
            payments_.add(
                input.readMessage(com.lending.proto.Payment.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        payments_ = java.util.Collections.unmodifiableList(payments_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.lending.proto.Enova.internal_static_com_lending_proto_PaymentList_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.lending.proto.Enova.internal_static_com_lending_proto_PaymentList_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.lending.proto.PaymentList.class, com.lending.proto.PaymentList.Builder.class);
  }

  public static final int PAYMENTS_FIELD_NUMBER = 1;
  private java.util.List<com.lending.proto.Payment> payments_;
  /**
   * <code>repeated .com.lending.proto.Payment payments = 1;</code>
   */
  public java.util.List<com.lending.proto.Payment> getPaymentsList() {
    return payments_;
  }
  /**
   * <code>repeated .com.lending.proto.Payment payments = 1;</code>
   */
  public java.util.List<? extends com.lending.proto.PaymentOrBuilder> 
      getPaymentsOrBuilderList() {
    return payments_;
  }
  /**
   * <code>repeated .com.lending.proto.Payment payments = 1;</code>
   */
  public int getPaymentsCount() {
    return payments_.size();
  }
  /**
   * <code>repeated .com.lending.proto.Payment payments = 1;</code>
   */
  public com.lending.proto.Payment getPayments(int index) {
    return payments_.get(index);
  }
  /**
   * <code>repeated .com.lending.proto.Payment payments = 1;</code>
   */
  public com.lending.proto.PaymentOrBuilder getPaymentsOrBuilder(
      int index) {
    return payments_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < payments_.size(); i++) {
      output.writeMessage(1, payments_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < payments_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, payments_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.lending.proto.PaymentList)) {
      return super.equals(obj);
    }
    com.lending.proto.PaymentList other = (com.lending.proto.PaymentList) obj;

    boolean result = true;
    result = result && getPaymentsList()
        .equals(other.getPaymentsList());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getPaymentsCount() > 0) {
      hash = (37 * hash) + PAYMENTS_FIELD_NUMBER;
      hash = (53 * hash) + getPaymentsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.lending.proto.PaymentList parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.lending.proto.PaymentList parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.lending.proto.PaymentList parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.lending.proto.PaymentList parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.lending.proto.PaymentList parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.lending.proto.PaymentList parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.lending.proto.PaymentList parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.lending.proto.PaymentList parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.lending.proto.PaymentList parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.lending.proto.PaymentList parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.lending.proto.PaymentList parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.lending.proto.PaymentList parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.lending.proto.PaymentList prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.lending.proto.PaymentList}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.lending.proto.PaymentList)
      com.lending.proto.PaymentListOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.lending.proto.Enova.internal_static_com_lending_proto_PaymentList_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.lending.proto.Enova.internal_static_com_lending_proto_PaymentList_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.lending.proto.PaymentList.class, com.lending.proto.PaymentList.Builder.class);
    }

    // Construct using com.lending.proto.PaymentList.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getPaymentsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (paymentsBuilder_ == null) {
        payments_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        paymentsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.lending.proto.Enova.internal_static_com_lending_proto_PaymentList_descriptor;
    }

    public com.lending.proto.PaymentList getDefaultInstanceForType() {
      return com.lending.proto.PaymentList.getDefaultInstance();
    }

    public com.lending.proto.PaymentList build() {
      com.lending.proto.PaymentList result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.lending.proto.PaymentList buildPartial() {
      com.lending.proto.PaymentList result = new com.lending.proto.PaymentList(this);
      int from_bitField0_ = bitField0_;
      if (paymentsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          payments_ = java.util.Collections.unmodifiableList(payments_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.payments_ = payments_;
      } else {
        result.payments_ = paymentsBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.lending.proto.PaymentList) {
        return mergeFrom((com.lending.proto.PaymentList)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.lending.proto.PaymentList other) {
      if (other == com.lending.proto.PaymentList.getDefaultInstance()) return this;
      if (paymentsBuilder_ == null) {
        if (!other.payments_.isEmpty()) {
          if (payments_.isEmpty()) {
            payments_ = other.payments_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensurePaymentsIsMutable();
            payments_.addAll(other.payments_);
          }
          onChanged();
        }
      } else {
        if (!other.payments_.isEmpty()) {
          if (paymentsBuilder_.isEmpty()) {
            paymentsBuilder_.dispose();
            paymentsBuilder_ = null;
            payments_ = other.payments_;
            bitField0_ = (bitField0_ & ~0x00000001);
            paymentsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getPaymentsFieldBuilder() : null;
          } else {
            paymentsBuilder_.addAllMessages(other.payments_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.lending.proto.PaymentList parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.lending.proto.PaymentList) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.lending.proto.Payment> payments_ =
      java.util.Collections.emptyList();
    private void ensurePaymentsIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        payments_ = new java.util.ArrayList<com.lending.proto.Payment>(payments_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.lending.proto.Payment, com.lending.proto.Payment.Builder, com.lending.proto.PaymentOrBuilder> paymentsBuilder_;

    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public java.util.List<com.lending.proto.Payment> getPaymentsList() {
      if (paymentsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(payments_);
      } else {
        return paymentsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public int getPaymentsCount() {
      if (paymentsBuilder_ == null) {
        return payments_.size();
      } else {
        return paymentsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public com.lending.proto.Payment getPayments(int index) {
      if (paymentsBuilder_ == null) {
        return payments_.get(index);
      } else {
        return paymentsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public Builder setPayments(
        int index, com.lending.proto.Payment value) {
      if (paymentsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePaymentsIsMutable();
        payments_.set(index, value);
        onChanged();
      } else {
        paymentsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public Builder setPayments(
        int index, com.lending.proto.Payment.Builder builderForValue) {
      if (paymentsBuilder_ == null) {
        ensurePaymentsIsMutable();
        payments_.set(index, builderForValue.build());
        onChanged();
      } else {
        paymentsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public Builder addPayments(com.lending.proto.Payment value) {
      if (paymentsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePaymentsIsMutable();
        payments_.add(value);
        onChanged();
      } else {
        paymentsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public Builder addPayments(
        int index, com.lending.proto.Payment value) {
      if (paymentsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePaymentsIsMutable();
        payments_.add(index, value);
        onChanged();
      } else {
        paymentsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public Builder addPayments(
        com.lending.proto.Payment.Builder builderForValue) {
      if (paymentsBuilder_ == null) {
        ensurePaymentsIsMutable();
        payments_.add(builderForValue.build());
        onChanged();
      } else {
        paymentsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public Builder addPayments(
        int index, com.lending.proto.Payment.Builder builderForValue) {
      if (paymentsBuilder_ == null) {
        ensurePaymentsIsMutable();
        payments_.add(index, builderForValue.build());
        onChanged();
      } else {
        paymentsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public Builder addAllPayments(
        java.lang.Iterable<? extends com.lending.proto.Payment> values) {
      if (paymentsBuilder_ == null) {
        ensurePaymentsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, payments_);
        onChanged();
      } else {
        paymentsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public Builder clearPayments() {
      if (paymentsBuilder_ == null) {
        payments_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        paymentsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public Builder removePayments(int index) {
      if (paymentsBuilder_ == null) {
        ensurePaymentsIsMutable();
        payments_.remove(index);
        onChanged();
      } else {
        paymentsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public com.lending.proto.Payment.Builder getPaymentsBuilder(
        int index) {
      return getPaymentsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public com.lending.proto.PaymentOrBuilder getPaymentsOrBuilder(
        int index) {
      if (paymentsBuilder_ == null) {
        return payments_.get(index);  } else {
        return paymentsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public java.util.List<? extends com.lending.proto.PaymentOrBuilder> 
         getPaymentsOrBuilderList() {
      if (paymentsBuilder_ != null) {
        return paymentsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(payments_);
      }
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public com.lending.proto.Payment.Builder addPaymentsBuilder() {
      return getPaymentsFieldBuilder().addBuilder(
          com.lending.proto.Payment.getDefaultInstance());
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public com.lending.proto.Payment.Builder addPaymentsBuilder(
        int index) {
      return getPaymentsFieldBuilder().addBuilder(
          index, com.lending.proto.Payment.getDefaultInstance());
    }
    /**
     * <code>repeated .com.lending.proto.Payment payments = 1;</code>
     */
    public java.util.List<com.lending.proto.Payment.Builder> 
         getPaymentsBuilderList() {
      return getPaymentsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.lending.proto.Payment, com.lending.proto.Payment.Builder, com.lending.proto.PaymentOrBuilder> 
        getPaymentsFieldBuilder() {
      if (paymentsBuilder_ == null) {
        paymentsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.lending.proto.Payment, com.lending.proto.Payment.Builder, com.lending.proto.PaymentOrBuilder>(
                payments_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        payments_ = null;
      }
      return paymentsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.lending.proto.PaymentList)
  }

  // @@protoc_insertion_point(class_scope:com.lending.proto.PaymentList)
  private static final com.lending.proto.PaymentList DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.lending.proto.PaymentList();
  }

  public static com.lending.proto.PaymentList getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PaymentList>
      PARSER = new com.google.protobuf.AbstractParser<PaymentList>() {
    public PaymentList parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PaymentList(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PaymentList> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PaymentList> getParserForType() {
    return PARSER;
  }

  public com.lending.proto.PaymentList getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

