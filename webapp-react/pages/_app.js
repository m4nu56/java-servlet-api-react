import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Home from './index'
import React from 'react'
import Head from 'next/head'

function MyApp ({ Component, pageProps }) {
  return (<>
    <Head>
      <title>Migrate your existing Java Servlet jsp app to a modern <span className='important'>API + React stack</span></title>
      <link rel="icon" href="/favicon.ico"/>
    </Head>
    <Home {...pageProps} />
  </>)
}

export default MyApp
