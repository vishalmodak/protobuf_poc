require 'rubygems/package'
require 'zlib'

TAR_LONGLINK = '././@LongLink'
protos_tgz_archive = '../domain-protos/build/distributions/domain-protos.tgz'
destination = 'tmp/'
protos_gen_dir = 'lib/gen/protos/'

task :generate_proto do
  Gem::Package::TarReader.new( Zlib::GzipReader.open protos_tgz_archive ) do |tar|
    tar.each do |entry|
      dest = nil
      if entry.full_name == TAR_LONGLINK
        dest = File.join destination, entry.read.strip
        next
      end
      dest ||= File.join destination, entry.full_name
      if entry.directory?
        File.delete dest if File.file? dest
        FileUtils.mkdir_p dest, :mode => entry.header.mode, :verbose => false
      elsif entry.file?
        FileUtils.rm_rf dest if File.directory? dest
        File.open dest, "wb" do |f|
          f.print entry.read
        end
        FileUtils.chmod entry.header.mode, dest, :verbose => false
      elsif entry.header.typeflag == '2' #Symlink!
        File.symlink entry.header.linkname, dest
      end
    end
  end

  FileUtils.mkdir_p protos_gen_dir, :mode => 0755, :verbose => false

  system("protoc tmp/protos/*.proto --ruby_out=#{protos_gen_dir} -I tmp/protos")
end

task :clean do

end
