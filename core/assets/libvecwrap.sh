#!/bin/bash

if [[ $# != 1 ]]; then
  >&2 echo "Usage"
  exit 1
fi

./LibVec < $1
exit 0