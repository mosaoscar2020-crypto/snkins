# Token server

Set environment variables:
LIVEKIT_URL=wss://your-livekit-host
LIVEKIT_API_KEY=...
LIVEKIT_API_SECRET=...
LIVEKIT_ROOM=livescreen

Then:
npm install
node server.js

Never place LIVEKIT_API_SECRET in Android, HTML, GitHub, or the browser.
