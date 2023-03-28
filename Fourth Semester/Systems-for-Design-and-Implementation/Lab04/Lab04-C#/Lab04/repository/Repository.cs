using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab03.repository
{
    public interface Repository<T, ID>
    {

        void add(T elem);

        void update(ID id, T elem);

        IEnumerable<T> findAll();
    }
}
