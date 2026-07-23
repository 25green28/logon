'use client';

import Image from "next/image";
import {useState} from "react";

export default function Home() {
  const [username, setUsername] = useState("username");
  const [email, setEmail] = useState("example@provider.com");

  return (
    <div className={"w-full flex flex-col items-start gap-8"}>
      <h1 className={"text-5xl"}>Hello, {username}</h1>
      <p>Your email address is {email}</p>
    </div>
  );
}
