import {
  Card,
  CardAction,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"

import {
  Field,
  FieldContent,
  FieldDescription,
  FieldError,
  FieldGroup,
  FieldLabel,
  FieldLegend,
  FieldSeparator,
  FieldSet,
  FieldTitle,
} from "@/components/ui/field"
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import Link from "next/link";

export default function Login() {
  return (
    <Card className={"w-full sm:w-96"}>
      <CardHeader>
        <CardTitle>Login</CardTitle>
        <CardDescription>Sign in into your existing account</CardDescription>
      </CardHeader>
      <CardContent>
        <FieldGroup>
          <Field>
            <FieldLabel htmlFor={"email"}>Email address</FieldLabel>
            <Input id={"email"} type={"email"} placeholder={"example@provider.com"} required={true}/>
            {/*<FieldError>Wrong email address</FieldError>*/}
          </Field>
          <Field>
            <FieldLabel htmlFor={"password"}>Password</FieldLabel>
            <Input id={"password"} type={"password"} placeholder={"••••••••"} required={true}/>
            {/*<FieldError>Password cannot be empty</FieldError>*/}
          </Field>
          <Field>
            <Button type={"submit"}>Log in</Button>
          </Field>
        </FieldGroup>
      </CardContent>
      <CardFooter>
        <p>{"Don't have an account?"}</p>
        <Link href={"/register"} className={"pl-1 underline hover:text-muted-foreground"}>Register here</Link>
      </CardFooter>
    </Card>
  )
}