FROM alpine:3.3

RUN apk add --update nodejs

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY app/package.json /usr/src/app
RUN npm install

COPY app /usr/src/app

EXPOSE 8600

CMD node server.js
