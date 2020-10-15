// Méthode centralisée pour effectuer les appels API
export default (url, token, method = 'GET', payload) => {

  //console.log(`urlApi call=${urlApi}`)
  const request = new window.Request(url, {
    method: method,
    credentials: 'include',
    headers: new window.Headers({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    }),
    body: payload ? JSON.stringify(payload) : null,
  })

  // @formatter:off
  return window.fetch(request)
  .then(response => checkStatus(response))
  .catch(error => window.Promise.reject(error))
  // @formatter:on
}

// Check HTTP Status and throw Error if not valid
const checkStatus = (response, mode = 'json') => {
  if (response.status < 200 || response.status >= 300) {
    console.error(`status not ok = ${response.status} ${response.statusText}`, response)
    throw new Error(`status not ok = ${response.status} ${response.statusText}`)
  }

  return mode ? response[mode]() : response
}

