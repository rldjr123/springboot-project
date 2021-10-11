#!/usr/bin/env bash
#쉬고있는 proffile 찾기 : real1이 사용중이면 real2가 쉬고있고 반대면 real1이 쉬고있음

function find_idle_profile()
{
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_CODE} -ge 400 ] #400보다 크면 (즉, 40x/50x에러 모두 포함)

  then
      CURRNET_PROFILE=real2
  else
      CURRNET_PROFILE=$(curl -s http://localhost/profile)
  fi

  if [ ${CURRNET_PROFILE} == real1 ]
  then
    IDLE_PROFILE=real2
  else
    IDLE_PROFILE=real1
  fi

  echo "${IDLE_PROFILE}"
}

function find_idle_port()
{
  IDLE_PROFILE="$(find_idle_profile)"

  if [ ${IDLE_PROFILE} == real1 ]
  then
    echo "8086"
  else
    echo "8087"
  fi
}