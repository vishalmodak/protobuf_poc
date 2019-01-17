:verbose: true
:concurrency: 10

:timeout: 30

:queues:
    - background

production:
    :concurrency: 25
staging:
    :concurrency: 15
development:
    :concurrency: 20

:schedule: