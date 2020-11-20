#!/usr/bin/env bash

curl http://localhost:80/api/binpicking  -d "$(cat request.json)"
