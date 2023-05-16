using System;
using System.Threading.Tasks;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Net.Http.Json;
using System.Text;
using model;
using Newtonsoft.Json;

namespace Org.Example.Start
{
    public class RestTest
    {
        private const string URL = "http://localhost:8080/api/proba";
        private readonly HttpClient httpClient = new HttpClient();

        private async Task<T> Execute<T>(Func<Task<T>> func)
        {
            try
            {
                return await func();
            }
            catch (HttpRequestException ex) when (ex.StatusCode >= System.Net.HttpStatusCode.BadRequest && ex.StatusCode < System.Net.HttpStatusCode.InternalServerError)
            {
                throw new Exception(ex.Message);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<Proba[]> GetAll()
        {
            return await Execute(async () =>
            {
                HttpResponseMessage response = await httpClient.GetAsync(URL);
                response.EnsureSuccessStatusCode();
                string responseBody = await response.Content.ReadAsStringAsync();
                return JsonConvert.DeserializeObject<Proba[]>(responseBody);
            });
        }

        public async Task<Proba> GetById(long id)
        {
            return await Execute(async () =>
            {
                HttpResponseMessage response = await httpClient.GetAsync($"{URL}/{id}");
                response.EnsureSuccessStatusCode();
                string responseBody = await response.Content.ReadAsStringAsync();
                return JsonConvert.DeserializeObject<Proba>(responseBody);
            });
        }

        public async Task<Proba> Create(Proba proba)
        {
            return await Execute(async () =>
            {
                HttpResponseMessage response = await httpClient.PostAsJsonAsync(URL, proba);
                response.EnsureSuccessStatusCode();
                string responseBody = await response.Content.ReadAsStringAsync();
                return JsonConvert.DeserializeObject<Proba>(responseBody);
            });
        }


        public async Task Update(String id ,Proba proba)
        {
            await Execute(async () =>
            {
                HttpResponseMessage response = await httpClient.PutAsJsonAsync($"{URL}/{id}", proba);
                response.EnsureSuccessStatusCode();
                return Task.CompletedTask;
            });
        }

        public async Task Delete(string id)
        {
            await Execute(async () =>
            {
                HttpResponseMessage response = await httpClient.DeleteAsync($"{URL}/{id}");
                response.EnsureSuccessStatusCode();
                return Task.CompletedTask;
            });
        }
    }
}
