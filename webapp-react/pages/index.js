import React, { useEffect, useState } from 'react'
import cookies from 'next-cookies'
import makeRequest from '../api'

function Home ({ cookies }) {
  const [login, setLogin] = useState('')
  const [user, setUser] = useState(null)

  useEffect(() => {
    const fetchUser = async () => {

      try {
        const user = await makeRequest(
          `http://localhost:8080/webapp/api/users/session/who-am-i`,
          'token',
        )
        setUser(user)
      } catch (e) {
        console.error(`Erreur lors du chargement du user par l'api: ${e.message}`)
      }
    }
    fetchUser()
  }, []) // eslint-disable-line

  const submitForm = async (e) => {
    e.preventDefault()
    const user = await makeRequest(
      `http://localhost:8080/webapp/api/users/session/update?login=${login}`,
      'token',
      'PUT',
    )
    console.log(user)

    try {
      const user = await makeRequest(
        `http://localhost:8080/webapp/api/users/session/who-am-i`,
        'token',
      )
      setUser(user)
    } catch (e) {
      console.error(`Erreur lors du chargement du user par l'api: ${e.message}`)
    }
  }

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <a className="navbar-brand" href="#">React</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"/>
        </button>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <a className="nav-link" href="http://localhost:8080/webapp">Module on Java Servlet</a>
            </li>
            <li className="nav-item active">
              <a className="nav-link" href="http://localhost:3000">Module on React SPA <span className="sr-only">(current)</span></a>
            </li>
          </ul>
        </div>
      </nav>

      <div className='container'>
        <h1>Migrate your existing Java Servlet jsp app to a modern API + React stack</h1>

        <br/>

        <div className='alert alert-dark'>
          This is the React SPA !
        </div>

        <h2>Session</h2>
        <ul className='list-group'>
          <li className='list-group-item'><b>Cookie</b>: <PrettyPrint jsonObj={cookies}/></li>
          <li className='list-group-item'><b>User requested from HttpSession</b>: <PrettyPrint jsonObj={user}/></li>
        </ul>

        <br/>

        <h2>Update User in Session with an API request</h2>
        <div className="alert alert-primary">You can update the user login in HttpSession using the below form</div>
        <form onSubmit={(e) => submitForm(e)}>
          <div className="form-group">
            <label htmlFor="login">Login</label>
            <input type="text" className="form-control" id="login" name="login" value={login} onChange={(e) => setLogin(e.target.value)}/>
          </div>
          <button type="submit" className="btn btn-primary">Submit</button>
        </form>

      </div>
    </>
  )
}

Home.getInitialProps = async (ctx) => {
  return {
    cookies: cookies(ctx),
  }
}

export default Home

function PrettyPrint (props) {
  return <pre>{JSON.stringify(props.jsonObj, null, 2)}</pre>
}
