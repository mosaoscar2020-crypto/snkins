import express from "express";
import { AccessToken } from "livekit-server-sdk";

const app=express();
const port=process.env.PORT || 8080;
const API_KEY=process.env.LIVEKIT_API_KEY;
const API_SECRET=process.env.LIVEKIT_API_SECRET;
const ROOM=process.env.LIVEKIT_ROOM || "livescreen";

app.get("/token", async (req,res)=>{
  if(!API_KEY || !API_SECRET) return res.status(500).send("Server credentials missing");
  const role=req.query.role==="host" ? "host" : "viewer";
  const identity=role+"-"+Math.random().toString(36).slice(2);
  const token=new AccessToken(API_KEY,API_SECRET,{identity,ttl:"1h"});
  token.addGrant({roomJoin:true,room:ROOM,canPublish:role==="host",canSubscribe:true});
  res.type("text/plain").send(await token.toJwt());
});
app.listen(port,()=>console.log("token server on",port));
