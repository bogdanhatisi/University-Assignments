using model;
using Org.Example.Start;

var client = new RestTest();

// await client.Delete("3");

var probe = await client.GetAll();

for (int i = 0; i < probe.Length; i++)
{
    Console.WriteLine(probe[i].ToString());
}

var proba = await client.GetById(4);
Console.WriteLine(proba.ToString());

var newProba = new Proba("bras", 200);

var addProba = await client.Create(newProba);
Console.WriteLine(addProba.ToString());

// await client.Update("3",newProba);