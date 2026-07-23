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

export default function Register() {
  return (
    <Card className={"w-full sm:w-96"}>
      <CardHeader>
        <CardTitle>Register</CardTitle>
        <CardDescription>Create a new personal account</CardDescription>
      </CardHeader>
      <CardContent>
        <FieldGroup>
          <Field>
            <FieldLabel htmlFor={"username"}>Username</FieldLabel>
            <Input id={"username"} type={"text"} placeholder={"Frog"} required={true}/>
          </Field>
          <Field>
            <FieldLabel htmlFor={"email"}>Email</FieldLabel>
            <Input id={"email"} type={"email"} placeholder={"example@provider.com"} required={true}/>
          </Field>
          <Field>
            <FieldLabel htmlFor={"password"}>Password</FieldLabel>
            <Input id={"password"} type={"password"} placeholder={"••••••••"} required={true}/>
          </Field>
          <Field>
            <FieldLabel htmlFor={"password-repeat"}>Repeat password</FieldLabel>
            <Input id={"password-repeat"} type={"password"} placeholder={"••••••••"} required={true}/>
          </Field>
          <Field>
            <Button type={"submit"}>Register</Button>
          </Field>
        </FieldGroup>
      </CardContent>
      <CardFooter>
        <p>Already have an account?</p>
        <Link href={"/login"} className={"pl-1 underline hover:text-muted-foreground"}>Log in here</Link>
      </CardFooter>
    </Card>
  )
}