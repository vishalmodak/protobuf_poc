require 'google/protobuf/well_known_types'

module V1
  class ProspectController < ApplicationController

    def lookupProspect

      business = Com::Lending::Proto::Business.new(name: "NocodeInc", fein: "123345")
      person = Com::Lending::Proto::Person.new(firstName: "Joe", lastName: "Jackson")
      prospect = Com::Lending::Proto::Prospect.new(business: business)
      prospect.person = person
      prospectAny = Google::Protobuf::Any.new
      prospectAny.pack(person)
      prospect.anyProspect = prospectAny
      respond_to do |format|
        format.json { render json: prospect }
        format.protobuf { render plain: prospect.to_proto, content_type: 'application/x-protobuf' }
      end
    end
  end
end