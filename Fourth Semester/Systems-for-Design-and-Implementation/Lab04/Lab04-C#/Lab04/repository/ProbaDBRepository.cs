using Lab03.domain;
using System.Collections.ObjectModel;
using System.Data;
using log4net;

namespace Lab03.repository;

public class ProbaDBRepository: ProbaRepository
{
    private static readonly ILog log = LogManager.GetLogger("ProbaDBRepository");
    IDictionary<String, string> props;

    public ProbaDBRepository(IDictionary<string, string> props)
    {
        this.props = props;
    }

    public void add(int elem)
    {
        throw new NotImplementedException();
    }

    public void update(Proba id, int elem)
    {
        throw new NotImplementedException();
    }

    public IEnumerator<int> findAll()
    {
        throw new NotImplementedException();
    }
    public Proba findById(int id)
    {
        log.InfoFormat("Entering findById Proba with value {0} ", id);
        IDbConnection con = DBUtils.getConnection(props);
        Proba proba = null;
        using (var comm = con.CreateCommand())
        {
            comm.CommandText = "select * from Probe where id=@id";
            var paramId = comm.CreateParameter();
            paramId.ParameterName = "@id";
            paramId.Value = id;
            comm.Parameters.Add(paramId);
            
            using (var dataR = comm.ExecuteReader())
            {
                while (dataR.Read())
                {
                    NumeProba nume = (NumeProba)Enum.Parse(typeof(NumeProba), dataR.GetString(0));
                    int idArbitru = dataR.GetInt16(1).
                    proba = new Proba(id, nume);
                    proba.Id=id;
                    proba.Nume = nume;

                }
            }
        }
        log.InfoFormat("Exiting findById with value {0}", null);
        return proba;

    }
}