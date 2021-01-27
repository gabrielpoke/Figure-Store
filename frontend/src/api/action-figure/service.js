import axios from 'axios'

const client = axios.create({
  baseURL: 'http://localhost:8080',
  json: true
})

export default {
  async execute (method, endpoint, data) {
    return client({
      method,
      url: endpoint,
      data,
      headers: {}
    }).then(req => {
      return req.data
    })
  },
  findAll () {
    return this.execute('GET', '/action-figures')
  },
  findById (id) {
    return this.execute('GET', `/action-figures/${id}`)
  },
  save (data) {
    return this.execute('POST', '/action-figures', data)
  },
  update (id, data) {
    return this.execute('PUT', `/action-figures/${id}`, data)
  },
  delete (id) {
    return this.execute('DELETE', `/action-figures/${id}`)
  }
}
