I have applied the design provided by the FIGMA, tried to keep it as comform as possible.
As for technical choices/decision, I applied the google recommendations by using
- retrofit for networking
- implemented the repository pattern (for a single source of truth)
- viewmodels to transfer data/state between different app sections.


What I would have added in a production context:
- biometrics authentication.
- local database for caching.
- rx for reactivity.
- activity timeout to logout the application.
- other features (templates, contacts list, quick actions... etc). 