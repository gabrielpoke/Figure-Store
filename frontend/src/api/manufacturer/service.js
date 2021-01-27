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
    return this.execute('GET', '/manufacturers')
  },
  findById (id) {
    return this.execute('GET', `/manufacturers/${id}`)
  },
  save (data) {
    return this.execute('POST', '/manufacturers', data)
  },
  update (id, data) {
    return this.execute('PUT', `/manufacturers/${id}`, data)
  },
  delete (id) {
    return this.execute('DELETE', `/manufacturers/${id}`)
  }
}
