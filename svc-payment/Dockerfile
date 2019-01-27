FROM ruby:2.4-slim

RUN apt-get update && apt-get install -y -f build-essential nodejs libpq-dev

RUN mkdir /app
WORKDIR /app

COPY Gemfile Gemfile.lock ./
RUN gem install bundler -v 2.0.0.pre1
RUN bundle install --binstubs

COPY . .

EXPOSE 3000

#CMD puma -C config/puma.rb
CMD ["bundle", "exec", "rails", "server", "-b", "0.0.0.0"]
