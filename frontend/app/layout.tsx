import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import { cn } from '@/lib/utils';
import './globals.css';

const inter = Inter({subsets:['latin'],variable:'--font-sans'});

export const metadata: Metadata = {
  title: 'LogOn',
  description: 'Logon made by Mateusz Laski',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html
      lang="en"
      className={cn("h-full", "font-sans", inter.variable)}
    >
      <body className="min-h-full flex flex-col justify-center items-center px-4 md:px-8 lg:px-16">{children}</body>
    </html>
  );
}
